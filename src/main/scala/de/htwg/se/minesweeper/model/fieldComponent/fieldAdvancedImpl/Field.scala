package de.htwg.se.minesweeper.model.fieldComponent.fieldAdvancedImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl.Field as BaseField
import de.htwg.se.minesweeper.model.Difficulty.Hard
import de.htwg.se.minesweeper.model.fieldComponent.FieldInterface

class Field @Inject() (@Named("normal") size: Int) extends BaseField(size, size, Hard) {

}
