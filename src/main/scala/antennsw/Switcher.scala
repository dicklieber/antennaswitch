package antennsw

trait Switcher:
  @throws [RuleViolation] ("If this connection would violate rules.")
  def switch(switchState: SwitchState): Unit

case class SwitchState(radio: Radio, maybeAntenna: Option[Antenna]) extends Ordered[SwitchState]:
  def compare(that: SwitchState): Int = radio.port compareTo that.radio.port

object SwitchState:
  def apply(entry: (Radio, Option[Antenna])): SwitchState =
    SwitchState(entry._1, entry._2)

  def apply(radio: Radio, antenna: Antenna): SwitchState =
    SwitchState(radio, Option(antenna))

case class RuleViolation(switchState: SwitchState) extends Exception(
  s"Aleady a connection to antenna: ${switchState.maybeAntenna}!"
)