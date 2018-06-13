package com.arrowmaker.lib

import java.io.StringWriter
import java.io.Writer
import javax.xml.stream.XMLOutputFactory
import javax.xml.stream.XMLStreamWriter

object Xml {
    fun create(writer: Writer = StringWriter()): XMLStreamWriter {
        val xmlOutputFactory = XMLOutputFactory.newInstance()
        return xmlOutputFactory.createXMLStreamWriter(writer)
    }
}