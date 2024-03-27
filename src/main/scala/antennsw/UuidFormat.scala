package antennsw

import play.api.libs.json.{Format, JsError, JsResult, JsString, JsSuccess, JsValue}

import java.util.UUID

implicit val UuidFormat: Format[UUID] = new Format[UUID] {
  override def reads(json: JsValue): JsResult[UUID] = {
    val ss = json.as[String]
    try {
      JsSuccess(UUID.fromString(ss))
    } catch {
      case e:Exception =>
        JsError(s"UUID: $ss could not parse UUID!")
    }
  }

  override def writes(uuid: UUID): JsValue = {
    JsString(uuid.toString)
  }
}
