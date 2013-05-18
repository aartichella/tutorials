package org.baeldung.spring.persistence.service;

import org.baeldung.spring.persistence.config.PersistenceConfig;
import org.baeldung.spring.persistence.model.Child;
import org.baeldung.spring.persistence.model.Parent;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class }, loader = AnnotationConfigContextLoader.class)
public class ParentServicePersistenceIntegrationTest {

    @Autowired
    private IParentService service;

    @Autowired
    private IChildService childService;

    @Autowired
    private SessionFactory sessionFactory;

    // tests

    @Test
    public final void whenContextIsBootstrapped_thenNoExceptions() {
        //
    }

    @Test
    public final void whenOneToOneEntitiesAreCreated_thenNoExceptions() {
        final Child childEntity = new Child();
        childService.create(childEntity);

        final Parent parentEntity = new Parent(childEntity);
        service.create(parentEntity);

        System.out.println("Child = " + childService.findOne(childEntity.getId()));
        System.out.println("Child - parent = " + childService.findOne(childEntity.getId()).getParent());

        System.out.println("Parent = " + service.findOne(parentEntity.getId()));
        System.out.println("Parent - child = " + service.findOne(parentEntity.getId()).getChild());
    }

    @Test
    public final void whenChildIsDeleted_thenDataException() {
        final Child childEntity = new Child();
        childService.create(childEntity);

        final Parent parentEntity = new Parent(childEntity);
        service.create(parentEntity);

        childService.delete(childEntity);
    }

}
