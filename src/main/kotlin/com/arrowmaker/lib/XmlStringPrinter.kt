package com.arrowmaker.lib

import java.io.StringWriter
import javax.xml.stream.XMLOutputFactory
import javax.xml.stream.XMLStreamWriter

object XmlStringPrinter: XmlPrinter<StringWriter> {
    override fun document(init: XMLStreamWriter.() -> Unit): StringWriter {
        val writer = StringWriter()

        writer.use {
            val xmlStreamWriter =
                    XMLOutputFactory
                            .newFactory()
                            .createXMLStreamWriter(it)

            xmlStreamWriter.document { init() }
        }

        return writer
    }
}