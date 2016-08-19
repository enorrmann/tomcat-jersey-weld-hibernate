package com.causy.persistence.hibernate;

import com.causy.model.Employee;
import com.causy.persistence.dao.EmployeeDAO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeDAOImplTest {
    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_be_able_to_create_entity_and_then_read_it() throws Exception {
        //when
        final int id = employeeDAO.create(new Employee("Thomas", "test"));
        final Employee actual = employeeDAO.get(id);

        //then
        assertThat(actual.getName()).isEqualTo("Thomas");
        assertThat(actual.getRole()).isEqualTo("test");
    }

    @Test
    public void should_not_create_entities_with_the_same_id() throws Exception {
        //when
        final int id1 = employeeDAO.create(new Employee("Thomas", "test"));
        final int id2 = employeeDAO.create(new Employee("Florian", "test"));

        final Employee actual = employeeDAO.get(id1);

        //then
        assertThat(actual.getName()).isEqualTo("Thomas");
        assertThat(actual.getRole()).isEqualTo("test");
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    public void should_be_able_to_update_existing_employee() throws Exception {
        //given
        final int id = employeeDAO.create(new Employee("Thomas", "test"));

        //when
        employeeDAO.update(new Employee(id, "Thomas", "admin"));

        //then
        assertThat(employeeDAO.get(id).getRole()).isEqualTo("admin");
    }

    @Test
    public void should_not_be_able_to_update_non_existing_employee() throws Exception {
        //given
        final int id = employeeDAO.create(new Employee("Thomas", "test"));

        //then
        expectedException.expect(IllegalStateException.class);

        //when
        employeeDAO.update(new Employee(id + 1, "Thomas", "admin"));

        //then
        assertThat(employeeDAO.get(id).getRole()).isEqualTo("test");
    }
}