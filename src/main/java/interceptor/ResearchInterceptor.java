package interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Interceptor that manage log operations on research method calls.
 * @author Matthieu Clochard
 */
@ResearchLog @Interceptor
public class ResearchInterceptor {
	
	/**
	 * Simulates a log on all research method calls.
	 * @param ctx the context of the interceptor invocation.
	 * @return .
	 */
	@AroundInvoke
	public Object researchLog(InvocationContext ctx) {
		System.out.println("#\t#Interception : ");
		
		System.out.println("#\t#\tMéthode : " + ctx.getMethod().getName() + "()");
		
		System.out.println("#\t#\tExécution : ");
		
		try {
			return ctx.proceed();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			System.out.println("#\t#Fin de l'interception");
		}
	}

}
