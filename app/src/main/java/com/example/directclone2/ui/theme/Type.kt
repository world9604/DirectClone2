package com.example.directclone2.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    /*
    - [headlineLarge]
    1. [Settings] > [Settings]
    2. [Connected devices] > [Connected devices]
     */
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    /*
    - [headlineMedium]
    1. [Connected devices] > [Connection preferences]
     */
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    headlineSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    //Backup Button
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.sp
    ),

    /*
    - [bodyLarge]
    1. [Battery - Smart charging detail] > [Life-X mode]
     */
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    titleMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    /*
    - [bodyMedium]
    1. [Battery - Smart charging detail] > [- Maximum charging level bolow 100%]
     */
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),

    displayLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    displayMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = 1.sp
    ),

    /*
    - [labelLarge]
    1. [Main / All Expand & Focused] > [Pre-installed Apps and Settings]
     */
    labelLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),

    /*
    - [labelMedium]
    1. [Main / All Expand & Focused] > [Program Buttons]
    2. [Settings] > [Network & Internet]
     */
    labelMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),

    /*
    - [labelSmall]
    1. [Main / All Expand & Focused] > [(BackUp File Selection)]
     */
    labelSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.sp
    )
)