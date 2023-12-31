package com.example.directclone2.ui.components.myiconpack

import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons = listOf(IconCloneLock, IconDelete, IconDirectClone, IconRestore)
    return __AllIcons!!
  }
