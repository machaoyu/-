package com.hwua.util;



import com.hwua.pojo.Syslog;
import com.hwua.pojo.Users;
import com.hwua.service.SyslogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;



@Aspect
@Component
public class AdviseUtil {
    private Syslog log=new Syslog();
    private Users user;
    @Autowired
    private SyslogService syslogService;

    @Pointcut("execution(* com.hwua.controller.*.*(..))")
    public void p1(){}

    @Before("p1()")
    public void logBefore()throws Exception{
            Date date = new Date();
           /* SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            System.out.println(dateFormat.format(date.getTime()));//访问时间*/
           log.setVisitTime(date);


    }

    @AfterReturning("p1()")
    public void logAfterReturning() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//切面获取request
        Subject currentUser = SecurityUtils.getSubject();
        user= (Users) currentUser.getPrincipal();
        if(user!=null) {
            String name = user.getUsername();//访问用户
            log.setUsername(name);
            String ip = request.getRemoteAddr();
            log.setIp(ip);
            String url = request.getRequestURI();
            log.setUrl(url);
            System.out.println("日志记录完毕....");
        }
    }

    @AfterThrowing("p1()")
    public void logAfterThrowing() {
        System.out.println("日志记录异常信息....");
    }

    @After("p1()")
    public void logAfterAfter() {
        System.out.println("释放日志资源....");
    }


    @Around("p1()")
    public Object logAround(ProceedingJoinPoint pjp)throws Exception{
        Object obj = null;
        Object[] args = pjp.getArgs();//得到切入点方法的参数值
        //调用目标方法
        //调用目标方法
        try {
            long startTime = System.currentTimeMillis();//开始时间
            logBefore();//前置通知
            obj = pjp.proceed(args);//指定目标方法
            String mothed=pjp.getSignature().getName();
            log.setMethod(mothed);
            logAfterReturning();//后置通知
            long endTime = System.currentTimeMillis();//结束时间
            log.setExecutionTime(endTime-startTime);
        } catch (Throwable throwable) {
            logAfterThrowing();//异常通知
            throwable.printStackTrace();
        } finally {
            logAfterAfter();
            syslogService.addSyslog(log);

        }
        return obj;
    }


}


