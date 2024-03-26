package antennsw

import enumeratum.{EnumEntry, Enum}
import enumeratum.EnumEntry.CapitalWords

/**
 * @param maxN    how many keys there can be for this kind,
 * @param needsFieldName
 * @param includeInNav
 */
sealed trait Mode extends EnumEntry
  with CapitalWords 


object Mode extends Enum[Mode]:

  def values: IndexedSeq[Mode] = findValues

  case object Standby extends Mode
  case object Manual extends Mode
  case object Automatic extends Mode
