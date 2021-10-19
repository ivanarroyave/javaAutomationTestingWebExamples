package co.com.sofka.soap.utils.poi;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.apache.log4j.Logger;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Personalización del Runner con el cual se puede determinar que busque y
 * modifique los .feature antes de ser ejecutados
 * 
 * @since 27/04/2018
 * @author carmarlo
 *
 */
public class RunnerPersonalizado extends Runner {

	private Class<CucumberWithSerenity> classValue;
	private CucumberWithSerenity cucumberWithSerenity;

	private static final Logger LOGGER = Logger.getLogger(RunnerPersonalizado.class);

	public RunnerPersonalizado(Class<CucumberWithSerenity> classValue) {
		this.classValue = classValue;
		try {
			cucumberWithSerenity = new CucumberWithSerenity(classValue);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public Description getDescription() {
		return cucumberWithSerenity.getDescription();
	}

	private void runAnnotatedMethods(Class<?> annotation) {
		if (!annotation.isAnnotation()) {
			return;
		}
		Method[] methods = this.classValue.getMethods();
		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			for (Annotation item : annotations) {
				if (item.annotationType().equals(annotation)) {
					try {
						method.invoke(null);
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
					break;
				}
			}
		}
	}

	@Override
	public void run(RunNotifier notifier) {
		try {
			runAnnotatedMethods(BeforeSuite.class);
			cucumberWithSerenity = new CucumberWithSerenity(classValue);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		cucumberWithSerenity.run(notifier);
	}
}
