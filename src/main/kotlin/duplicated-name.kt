import kotlinx.html.DIV
import kotlinx.html.div
import kotlinx.html.h2

@JvmInline
value class CompanyName(val value: String)

@JvmInline
value class CompanyAddress(val value: String)

data class Logo(val src: String, val styleClass: String)

enum class Auctioneer(
  val companyName: CompanyName,
  val address: CompanyAddress,
  val kvkNumber: String,
  val contact: String,
  val logo: Logo,
) {
  BVA(
    CompanyName("BVA Auctions B.V."),
    CompanyAddress("Kryptonweg 8, 3812 RZ Amersfoort, The Netherlands"),
    "32118690",
    "www.bva-auctions.com",
    Logo("bva-logo.png", "bva-logo")
  ),
  TROOSTWIJK(
    CompanyName("Troostwijk Veilingen B.V."),
    CompanyAddress("Overschiestraat 59, 1062 XD Amsterdam, The Netherlands"),
    "33170931",
    "www.troostwijkauctions.com",
    Logo("twk-logo.png", "twk-logo")
  )
}

@Suppress("MaxLineLength")
data class AuctioneerDetailsComponent(
  val auctioneer: Auctioneer
) {

  operator fun invoke(div: DIV) {
    div.apply {
      div("terms") {
        h2 { localized { +"footer.auctioneerDetails.title" } }
        div("terms-address") {
          div { +auctioneer.companyName.value }
          div {
            localized(
              auctioneer.address.value,
              auctioneer.kvkNumber
            ) { +"footer.auctioneerDetails.info" }
          }
        }
        div { localized{+"footer.auctioneerDetails.termsAndConditions1"} }
        div { localized{+"footer.auctioneerDetails.termsAndConditions2"} }
      }
    }
  }
}
