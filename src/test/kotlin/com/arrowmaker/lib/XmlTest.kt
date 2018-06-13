package com.arrowmaker.lib

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.StringWriter
import java.util.*
import javax.xml.stream.XMLOutputFactory.newInstance

class XmlTest {

    @Test
    fun `Valid XML`() {

        Xml.create().document {  }


        val stringWriter = StringWriter()
        val xmlOutputFactory = newInstance()
        val writer = xmlOutputFactory.createXMLStreamWriter(stringWriter)


        val USD: Currency = Currency.getInstance("USD")
        val EUR: Currency = Currency.getInstance("EUR")

        val persons = listOf(
                Person(Name("John", "Doe"),
                        listOf(
                                Account("JP Morgan Chase & Co", "000-111-222-333", USD),
                                Account("Goldman Sachs", "000-111-222-444", EUR))),
                Person(Name("Jane", "Doe"),
                        listOf(
                                Account("JP Morgan Chase & Co", "000-777-222-333", USD),
                                Account("Goldman Sachs", "000-777-222-444", EUR))))

        val document = writer.document {
            element("persons") {
                attribute("version", "1.1")
                persons.forEach { (name, accounts) ->
                    element("person") {
                        element("name") {
                            element("first", name.first)
                            element("last", name.last)
                        }
                        element("accounts") {
                            accounts.forEach { (bank, number, currency) ->
                                element("account") {
                                    element("bank", bank)
                                    element("number", number)
                                    element("currency", currency.currencyCode)
                                }
                            }
                        }
                    }
                }
            }
        }

        val string = stringWriter.buffer.toString()
        stringWriter.close()

        assertEquals(xml, string)
    }
}

data class Name(val first: String, val last: String)

data class Account(val bank: String, val number: String, val currency: Currency)

data class Person(val name: Name, val accounts: List<Account>)

val xml =
        "<?xml version=\"1.0\" ?><persons version=\"1.1\">" +
                "<person>" +
                "<name>" +
                "<first>John</first>" +
                "<last>Doe</last>" +
                "</name>" +
                "<accounts>" +
                "<account>" +
                "<bank>JP Morgan Chase &amp; Co</bank>" +
                "<number>000-111-222-333</number>" +
                "<currency>USD</currency>" +
                "</account>" +
                "<account>" +
                "<bank>Goldman Sachs</bank>" +
                "<number>000-111-222-444</number>" +
                "<currency>EUR</currency>" +
                "</account>" +
                "</accounts>" +
                "</person>" +
                "<person>" +
                "<name>" +
                "<first>Jane</first>" +
                "<last>Doe</last>" +
                "</name>" +
                "<accounts>" +
                "<account>" +
                "<bank>JP Morgan Chase &amp; Co</bank>" +
                "<number>000-777-222-333</number>" +
                "<currency>USD</currency>" +
                "</account>" +
                "<account>" +
                "<bank>Goldman Sachs</bank>" +
                "<number>000-777-222-444</number>" +
                "<currency>EUR</currency>" +
                "</account>" +
                "</accounts>" +
                "</person>" +
                "</persons>"