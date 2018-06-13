package com.arrowmaker.lib


import java.io.Writer
import javax.xml.stream.XMLStreamWriter

interface  XmlPrinter <T: Writer> {
    fun document(init: XMLStreamWriter.() -> Unit): T
}