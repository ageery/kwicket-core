package org.kwicket.model

import org.apache.wicket.model.IModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.Serializable
import java.util.ArrayList

class ModelsTest {

    private class Person(
        val name: String? = null,
        val parent: Person? = null,
        val isTall: Boolean = false,
        val smoker: Boolean? = null
    ) : Serializable

    @Test
    fun `test that the obj extension property returns the model object`() {
        val model = IModel<String> { "test" }
        assertEquals("test", model.obj)
    }

    @Test
    fun `test that the model extension method wraps the receiver in a Model class`() {
        val person = Person(name = "test")
        val model = person.model()
        assertEquals(person, model.obj)
    }

    @Test
    fun `test that a model with an initial null object can have a non-null object set to it`() {
        val model: IModel<Person?> = null.model()
        assertEquals(null, model.obj)
        model.obj = Person(name = "test")
        assertEquals("test", model.obj?.name)
    }

    @Test
    fun `test that a list model has the correct contents`() {
        val list = listOf("A", "B", "C")
        val model = list.listModel()
        assertEquals(list, model.obj)
    }

    @Test
    fun `test that a list model works with a subclass of List`() {
        val list = ArrayList(listOf("A", "B", "C"))
        val model = list.listModel()
        assertEquals(list, model.obj)
    }

    @Test
    fun `test that a non-null model obj + non-null prop = prop value`() {
        val personModel = Person(name = "test name").model()
        val personNameModel = personModel + Person::name
        assertEquals("test name", personNameModel.obj)
    }

    @Test
    fun `test that a null model obj + prop = null`() {
        val personModel = Person().model()
        val personNameModel = personModel + Person::name
        assertEquals(null, personNameModel.obj)
    }

    @Test
    fun `test that a non-null model obj + null prop = null`() {
        val personModel = Person(name = "test name", parent = null).model()
        val personParentModel = personModel + Person::parent
        assertEquals(null, personParentModel.obj)
    }

    @Test
    fun `test that a non-null model obj + non-null prop1 + non-null prop2 = prop2 value`() {
        val parent = Person(name = "parent name")
        val personModel = Person(name = "child name", parent = parent).model()
        val parentNameModel = personModel + Person::parent + Person::name
        assertEquals("parent name", parentNameModel.obj)
    }

    @Test
    fun `test that a non-null model obj + null prop1 + null prop2 = null`() {
        val personModel = Person(name = "test").model()
        val parentNameModel = personModel + Person::parent + Person::name
        assertEquals(null, parentNameModel.obj)
    }

    @Test
    fun `test that a null model obj + prop1 + prop2 = null`() {
        val personModel: IModel<Person?> = null.model()
        val parentNameModel = personModel + Person::parent + Person::name
        assertEquals(null, parentNameModel.obj)
    }

    @Test
    fun `test that a non-null model obj + boolean prop1 = boolean`() {
        val personModel = Person(name = "test", isTall = false).model()
        val tallModel = personModel + Person::isTall
        assertEquals(false, tallModel.obj)
    }

    @Test
    fun `test that a non-null model obj + nullable prop1 = Boolean`() {
        val personModel = Person(name = "test", smoker = true).model()
        val smokerModel = personModel + Person::smoker
        assertEquals(true, smokerModel.obj)
    }

}