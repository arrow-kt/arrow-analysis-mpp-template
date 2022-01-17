import kotlinx.html.HTMLTag
import kotlinx.html.HtmlInlineTag
import kotlinx.html.TagConsumer
import kotlinx.html.Tag
import kotlinx.html.Unsafe
import java.text.MessageFormat
import java.util.Locale
import java.util.ResourceBundle

class LocalizedTag(val arguments: List<String>, consumer: TagConsumer<*>) :
  HTMLTag(
    "localized", consumer, emptyMap(),
    inlineTag = true,
    emptyTag = false
  ), HtmlInlineTag

private class LocalizedTagConsumer<T>(
  val downstream: TagConsumer<T>,
  locale: Locale
) : TagConsumer<T> by downstream {

  var shouldLocalize = false
  var arguments = emptyList<String>()
  val messages = ResourceBundle.getBundle("messages", locale)

  override fun onTagStart(tag: Tag) {
    if (tag is LocalizedTag) {
      shouldLocalize = true
      arguments = tag.arguments
    }
    downstream.onTagStart(tag)
  }

  override fun onTagEnd(tag: Tag) {
    if (tag is LocalizedTag) {
      shouldLocalize = false
      arguments = emptyList()
    }
    downstream.onTagEnd(tag)
  }

  override fun onTagContent(content: CharSequence) {
    val processedContent = if (shouldLocalize) {
      content.toString().localize()
    } else {
      content
    }
    downstream.onTagContent(processedContent)
  }

  override fun onTagContentUnsafe(block: Unsafe.() -> Unit) {
    val processedBlock = if (shouldLocalize) {
      {
        +localizedUnsafe()
          .apply { block() }
          .toString()
      }
    } else {
      block
    }
    downstream.onTagContentUnsafe(processedBlock)
  }

  private fun localizedUnsafe() = object : Unsafe {
    private val sb = StringBuilder()

    override operator fun String.unaryPlus() {
      sb.append(localize())
    }

    override fun toString(): String = sb.toString()
  }

  @Suppress("SpreadOperator")
  private fun String.localize(): String = MessageFormat.format(
    messages.getString(this),
    *arguments.toTypedArray()
  )
}
