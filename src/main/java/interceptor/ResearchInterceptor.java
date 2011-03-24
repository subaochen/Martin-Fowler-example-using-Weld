package interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@ResearchLog @Interceptor
public class ResearchInterceptor {
	
	@AroundInvoke
	public Object researchLog(InvocationContext ctx) {
		System.err.println("Interception : ");
		
		System.err.println("\tM�thode : " + ctx.getMethod());
		
		System.err.println("Ex�cution : ");
		
		try {
			return ctx.proceed();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			System.err.println("Fin de l'interception");
		}
	}

}
