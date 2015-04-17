package edu.bcm.dldcc.big.nursa.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jboss.solder.core.ExtensionManaged;

import edu.bcm.dldcc.big.nursa.util.qualifier.NoConversationDatabase;
import edu.bcm.dldcc.big.nursa.util.qualifier.UserDatabase;


public class Resources {
	@ExtensionManaged
	@UserDatabase
	@Produces
	@ConversationScoped
	@PersistenceUnit(unitName = "NURSA")
	private EntityManagerFactory objectEntityManager;

	@ExtensionManaged
	@Produces
	@ConversationScoped
	@PersistenceUnit(unitName = "NURSA")
	private EntityManagerFactory securityEntityManager;

	@NoConversationDatabase
	@ExtensionManaged
	@Produces
	@PersistenceUnit(unitName = "NURSA")
	private EntityManagerFactory noConvoEntityManager;

	
	
	 
	@SuppressWarnings("unchecked")
	public static <T> T lookup(BeanManager manager, Class<T> beanClass,
			Annotation... qualifiers) {
		return (T) lookup(manager, (Type) beanClass, qualifiers);
	}

	@SuppressWarnings({ "unchecked" })
	public static Object lookup(BeanManager manager, Type beanType,
			Annotation... qualifiers) {
		Set<?> beans = manager.getBeans(beanType, qualifiers);
		if (beans.size() != 1) {
			if (beans.size() == 0) {
				throw new RuntimeException("No beans of class " + beanType
						+ "found.");
			} else {
				throw new RuntimeException("Multiple beans of class "
						+ beanType + " found: " + beans + ".");
			}
		}

		@SuppressWarnings("rawtypes")
		Bean myBean = (Bean) beans.iterator().next();

		return manager.getReference(myBean, beanType,
				manager.createCreationalContext(myBean));
	}

	@SuppressWarnings({ "unchecked" })
	public static <T> T lookup(Class<T> beanClass, Annotation... qualifiers) {
		return (T) lookup((Type) beanClass, qualifiers);
	}

	public static Object lookup(Type beanType, Annotation... qualifiers) {
		return lookup(getBeanManager(), beanType, qualifiers);
	}

	@SuppressWarnings({ "unchecked" })
	public static <T> T lookup(BeanManager manager, String name) {
		Set<?> beans = manager.getBeans(name);
		if (beans.size() != 1) {
			if (beans.size() == 0) {
				throw new RuntimeException("No beans with name " + name
						+ "found.");
			} else {
				throw new RuntimeException("Multiple beans with name " + name
						+ " found: " + beans + ".");
			}
		}

		Bean<T> myBean = (Bean<T>) beans.iterator().next();

		return (T) manager.getReference(myBean, myBean.getBeanClass(),
				manager.createCreationalContext(myBean));
	}

	public static <T> T lookup(String name) {
		return Resources.<T> lookup(getBeanManager(), name);
	}

	private static BeanManager getBeanManager() {
		try {
			return (BeanManager) new InitialContext()
					.lookup("java:comp/BeanManager");
		} catch (NamingException e) {
			try {
				return (BeanManager) new InitialContext()
						.lookup("java:app/BeanManager");
			} catch (NamingException e1) {
				throw new RuntimeException(e1);
			}

		}
	}
}
