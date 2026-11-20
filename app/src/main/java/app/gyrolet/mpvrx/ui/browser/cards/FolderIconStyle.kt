/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package app.gyrolet.mpvrx.ui.browser.cards

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun FolderStyleIcon(
    styleId: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color,
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val c = tint
        val gold = Color(0xFFFFC857)
        val orange = Color(0xFFE88732)
        val blue = Color(0xFF3987E8)
        val purple = Color(0xFF8056CE)
        val green = Color(0xFF20A879)
        val gradient = Brush.linearGradient(
            colors = when (styleId.coerceIn(0, 23)) {
                2, 6, 8, 14 -> listOf(Color(0xFFFFE18A), Color(0xFFFFA33C), Color(0xFFB95724))
                3, 9, 15 -> listOf(Color(0xFF9CEBFF), blue, Color(0xFF2450A8))
                5, 10, 16 -> listOf(Color(0xFFD7B8FF), purple, Color(0xFF45228E))
                7, 11, 17 -> listOf(Color(0xFF9AF2C5), green, Color(0xFF08634F))
                12 -> listOf(Color(0xFFFFA8C8), Color(0xFFD83D83))
                13 -> listOf(Color(0xFFB9C8D8), Color(0xFF52677F))
                18 -> listOf(Color(0xFFFFE5A3), Color(0xFFDB8D25))
                19 -> listOf(Color(0xFFB7F4EE), Color(0xFF168D9B))
                20 -> listOf(Color(0xFF65D9E8), Color(0xFF3765C8), Color(0xFF9B70E8))
                21 -> listOf(Color(0xFFE8C28B), Color(0xFF95613D), Color(0xFF573A32))
                22 -> listOf(Color(0xFFFFF5D9), Color(0xFFE8D5AA), Color(0xFFC5A875))
                23 -> listOf(Color(0xFFFF8A65), Color(0xFF9575CD), Color(0xFF4DB6AC))
                else -> listOf(c.copy(alpha = .96f), c.copy(alpha = .58f))
            },
            start = Offset(0f, 0f),
            end = Offset(w, h),
        )

        fun folderPath(open: Boolean = false): Path = Path().apply {
            moveTo(w * .08f, h * .28f)
            lineTo(w * .39f, h * .28f)
            lineTo(w * .49f, h * .39f)
            lineTo(w * .88f, h * .39f)
            if (open) {
                lineTo(w * .96f, h * .51f)
                lineTo(w * .79f, h * .84f)
                lineTo(w * .12f, h * .84f)
            } else {
                lineTo(w * .91f, h * .76f)
                quadraticTo(w * .91f, h * .84f, w * .82f, h * .84f)
                lineTo(w * .15f, h * .84f)
                quadraticTo(w * .08f, h * .84f, w * .08f, h * .76f)
            }
            close()
        }

        fun roundedFolder(inset: Float = .08f): Path = Path().apply {
            moveTo(w * inset, h * .31f)
            lineTo(w * .39f, h * .31f)
            lineTo(w * .49f, h * .41f)
            lineTo(w * (1f - inset), h * .41f)
            lineTo(w * (1f - inset), h * .76f)
            quadraticTo(w * (1f - inset), h * .85f, w * .84f, h * .85f)
            lineTo(w * .16f, h * .85f)
            quadraticTo(w * inset, h * .85f, w * inset, h * .76f)
            close()
        }

        when (styleId.coerceIn(0, 23)) {
            0 -> drawPath(folderPath(), gradient)
            1 -> {
                drawPath(folderPath(), gradient)
                drawPath(
                    Path().apply {
                        moveTo(w * .12f, h * .47f)
                        lineTo(w * .88f, h * .47f)
                        lineTo(w * .78f, h * .78f)
                        lineTo(w * .18f, h * .78f)
                        close()
                    },
                    Brush.verticalGradient(listOf(c.copy(alpha = .2f), c.copy(alpha = .8f))),
                )
            }
            2 -> {
                drawPath(folderPath(), Brush.linearGradient(listOf(Color(0xFFFFD66B), Color(0xFFB56A20))))
                drawPath(
                    Path().apply {
                        moveTo(w * .08f, h * .48f); lineTo(w * .92f, h * .48f)
                        lineTo(w * .82f, h * .86f); lineTo(w * .15f, h * .86f); close()
                    },
                    Brush.verticalGradient(listOf(Color(0xFFFFC45C), Color(0xFFE47B24))),
                )
                drawLine(Color.White.copy(alpha = .4f), Offset(w * .18f, h * .56f), Offset(w * .78f, h * .56f), w * .025f)
            }
            3 -> {
                drawRoundRect(
                    Brush.linearGradient(listOf(Color(0xFF65D8FF), blue)),
                    topLeft = Offset(w * .03f, h * .03f),
                    size = Size(w * .94f, h * .94f),
                    cornerRadius = CornerRadius(w * .18f),
                )
                drawPath(folderPath(), Brush.linearGradient(listOf(Color.White, Color(0xFFD8F3FF))))
            }
            4 -> {
                drawPath(folderPath(), c.copy(alpha = .10f))
                drawPath(folderPath(), c, style = Stroke(width = (w * .065f).coerceAtLeast(1f)))
                drawLine(c, Offset(w * .13f, h * .48f), Offset(w * .84f, h * .48f), w * .035f)
            }
            5 -> {
                drawPath(
                    Path().apply {
                        moveTo(w * .16f, h * .20f); lineTo(w * .72f, h * .20f)
                        lineTo(w * .88f, h * .35f); lineTo(w * .88f, h * .69f)
                        lineTo(w * .16f, h * .69f); close()
                    }, c.copy(alpha = .45f),
                )
                drawPath(folderPath(), gradient)
            }
            6 -> {
                drawPath(folderPath(), Brush.linearGradient(listOf(Color(0xFFFFD878), orange, Color(0xFF9C4D1C))))
                drawPath(
                    Path().apply {
                        moveTo(w * .10f, h * .49f); lineTo(w * .90f, h * .49f)
                        lineTo(w * .80f, h * .83f); lineTo(w * .17f, h * .83f); close()
                    }, Brush.verticalGradient(listOf(Color(0xFFFFE39A), Color(0xFFD96B25))),
                )
                drawLine(Color.White.copy(alpha = .6f), Offset(w * .10f, h * .49f), Offset(w * .90f, h * .49f), w * .025f)
            }
            7 -> {
                drawPath(folderPath(true), gradient)
                drawCircle(Color.White.copy(alpha = .9f), w * .075f, Offset(w * .73f, h * .25f))
                drawCircle(green, w * .045f, Offset(w * .73f, h * .25f))
            }
            8 -> {
                drawPath(folderPath(), Brush.linearGradient(listOf(Color(0xFFFFF0B3), Color(0xFFFFB52E), Color(0xFFE56C24))))
                drawPath(
                    Path().apply {
                        moveTo(w * .12f, h * .55f); lineTo(w * .87f, h * .55f)
                        lineTo(w * .80f, h * .79f); lineTo(w * .18f, h * .79f); close()
                    }, Color.White.copy(alpha = .22f),
                )
                drawLine(Color.White.copy(alpha = .65f), Offset(w * .17f, h * .42f), Offset(w * .72f, h * .42f), w * .035f)
            }
            9 -> {
                drawPath(folderPath(), Color(0xFF173D73).copy(alpha = .35f))
                drawPath(
                    Path().apply {
                        moveTo(w * .08f, h * .28f); lineTo(w * .40f, h * .28f)
                        lineTo(w * .50f, h * .39f); lineTo(w * .90f, h * .39f)
                        lineTo(w * .90f, h * .77f); lineTo(w * .08f, h * .77f); close()
                    }, gradient,
                )
                drawLine(Color.White.copy(alpha = .7f), Offset(w * .15f, h * .45f), Offset(w * .82f, h * .45f), w * .025f)
            }
            10 -> {
                drawRoundRect(
                    Brush.linearGradient(listOf(Color(0xFF34205F), purple)),
                    topLeft = Offset(w * .05f, h * .13f),
                    size = Size(w * .90f, h * .76f),
                    cornerRadius = CornerRadius(w * .16f),
                )
                drawPath(folderPath(), Brush.linearGradient(listOf(Color(0xFFE7D7FF), Color(0xFF9D7BE8))))
                drawLine(Color.White.copy(alpha = .75f), Offset(w * .17f, h * .45f), Offset(w * .76f, h * .45f), w * .025f)
            }
            11 -> {
                drawPath(folderPath(true), Brush.linearGradient(listOf(Color(0xFFB9F8D7), green, Color(0xFF075B4B))))
                drawPath(
                    Path().apply {
                        moveTo(w * .20f, h * .52f); lineTo(w * .77f, h * .52f)
                        lineTo(w * .67f, h * .73f); lineTo(w * .24f, h * .73f); close()
                    }, Color.White.copy(alpha = .25f),
                )
                drawCircle(Color.White, w * .055f, Offset(w * .75f, h * .27f))
            }
            12 -> {
                drawPath(roundedFolder(.07f), gradient)
                drawRoundRect(
                    Color.White.copy(alpha = .2f),
                    topLeft = Offset(w * .17f, h * .53f),
                    size = Size(w * .66f, h * .20f),
                    cornerRadius = CornerRadius(w * .06f),
                )
                drawLine(Color.White.copy(alpha = .8f), Offset(w * .18f, h * .48f), Offset(w * .80f, h * .48f), w * .025f)
            }
            13 -> {
                drawPath(folderPath(), Color(0xFF34495E).copy(alpha = .3f))
                drawPath(folderPath(), Brush.linearGradient(listOf(Color(0xFFE3EDF5), Color(0xFF8299AD), Color(0xFF40566C))))
                drawPath(folderPath(), Color.White.copy(alpha = .22f), style = Stroke(width = w * .025f))
                drawLine(Color.White.copy(alpha = .65f), Offset(w * .14f, h * .47f), Offset(w * .83f, h * .47f), w * .02f)
            }
            14 -> {
                drawPath(folderPath(), gradient)
                drawPath(
                    Path().apply {
                        moveTo(w * .12f, h * .50f); lineTo(w * .88f, h * .50f)
                        lineTo(w * .78f, h * .82f); lineTo(w * .18f, h * .82f); close()
                    }, Brush.verticalGradient(listOf(Color(0xFFFFE7A8), Color(0xFFE58B2D))),
                )
                drawCircle(Color.White.copy(alpha = .8f), w * .045f, Offset(w * .72f, h * .62f))
                drawCircle(Color.White.copy(alpha = .5f), w * .025f, Offset(w * .60f, h * .68f))
            }
            15 -> {
                drawRoundRect(
                    Brush.linearGradient(listOf(Color(0xFF142D52), Color(0xFF357BCB))),
                    topLeft = Offset(w * .04f, h * .08f),
                    size = Size(w * .92f, h * .84f),
                    cornerRadius = CornerRadius(w * .20f),
                )
                drawPath(folderPath(), Brush.linearGradient(listOf(Color(0xFFE4F7FF), Color(0xFF83C8F5))))
                drawLine(Color.White, Offset(w * .17f, h * .46f), Offset(w * .78f, h * .46f), w * .025f)
            }
            16 -> {
                drawPath(folderPath(), gradient)
                drawPath(
                    Path().apply {
                        moveTo(w * .12f, h * .48f); lineTo(w * .88f, h * .48f)
                        lineTo(w * .79f, h * .82f); lineTo(w * .18f, h * .82f); close()
                    }, Color(0xFF3E247B).copy(alpha = .25f),
                )
                drawPath(folderPath(), Color.White.copy(alpha = .55f), style = Stroke(width = w * .025f))
            }
            17 -> {
                drawPath(folderPath(true), gradient)
                drawRoundRect(
                    Color.White.copy(alpha = .25f),
                    topLeft = Offset(w * .28f, h * .53f),
                    size = Size(w * .42f, h * .18f),
                    cornerRadius = CornerRadius(w * .04f),
                )
                drawLine(Color.White.copy(alpha = .8f), Offset(w * .15f, h * .44f), Offset(w * .80f, h * .44f), w * .025f)
            }
            18 -> {
                drawPath(folderPath(), gradient)
                drawPath(
                    Path().apply {
                        moveTo(w * .13f, h * .48f); lineTo(w * .87f, h * .48f)
                        lineTo(w * .79f, h * .82f); lineTo(w * .18f, h * .82f); close()
                    }, Color(0xFF8B4B18).copy(alpha = .22f),
                )
                drawPath(folderPath(), Color.White.copy(alpha = .55f), style = Stroke(width = w * .018f))
                drawCircle(Color.White.copy(alpha = .85f), w * .035f, Offset(w * .76f, h * .24f))
            }
            19 -> {
                drawRoundRect(
                    Brush.linearGradient(listOf(Color(0xFF0D5364), Color(0xFF16A6A0))),
                    topLeft = Offset(w * .04f, h * .08f),
                    size = Size(w * .92f, h * .84f),
                    cornerRadius = CornerRadius(w * .22f),
                )
                drawPath(folderPath(true), Brush.linearGradient(listOf(Color(0xFFD7FFFA), Color(0xFF65D9CC))))
                drawLine(Color.White.copy(alpha = .85f), Offset(w * .16f, h * .45f), Offset(w * .82f, h * .45f), w * .025f)
                drawCircle(Color.White, w * .035f, Offset(w * .75f, h * .27f))
            }
            20 -> {
                val wave1 = Path().apply {
                    moveTo(w * .08f, h * .38f)
                    cubicTo(w * .30f, h * .08f, w * .56f, h * .72f, w * .92f, h * .34f)
                }
                val wave2 = Path().apply {
                    moveTo(w * .06f, h * .55f)
                    cubicTo(w * .32f, h * .25f, w * .61f, h * .91f, w * .94f, h * .51f)
                }
                val wave3 = Path().apply {
                    moveTo(w * .08f, h * .72f)
                    cubicTo(w * .35f, h * .48f, w * .62f, h * .98f, w * .91f, h * .69f)
                }
                drawPath(wave1, gradient, style = Stroke(width = w * .105f))
                drawPath(wave2, Color(0xFF55D6C2), style = Stroke(width = w * .085f))
                drawPath(wave3, Color(0xFF8E79E8), style = Stroke(width = w * .065f))
            }
            21 -> {
                drawRoundRect(
                    Brush.linearGradient(listOf(Color(0xFF6B4939), Color(0xFF302A32))),
                    topLeft = Offset(w * .12f, h * .13f),
                    size = Size(w * .76f, h * .74f),
                    cornerRadius = CornerRadius(w * .16f),
                )
                val receiver = Path().apply {
                    moveTo(w * .27f, h * .35f)
                    cubicTo(w * .18f, h * .18f, w * .34f, h * .12f, w * .43f, h * .25f)
                    lineTo(w * .57f, h * .39f)
                    cubicTo(w * .69f, h * .50f, w * .77f, h * .36f, w * .72f, h * .29f)
                    lineTo(w * .82f, h * .22f)
                    cubicTo(w * .98f, h * .38f, w * .78f, h * .61f, w * .64f, h * .58f)
                    cubicTo(w * .48f, h * .55f, w * .29f, h * .42f, w * .27f, h * .35f)
                    close()
                }
                drawPath(receiver, Color(0xFFE8C28B))
                drawCircle(Color(0xFFE8C28B), w * .13f, Offset(w * .50f, h * .72f))
                drawCircle(Color(0xFF49352F), w * .095f, Offset(w * .50f, h * .72f))
                for (i in 0..7) {
                    val angle = (i * Math.PI / 4.0)
                    drawCircle(
                        Color(0xFFE8C28B),
                        w * .018f,
                        Offset(
                            w * (.50f + .065f * kotlin.math.cos(angle).toFloat()),
                            h * (.72f + .065f * kotlin.math.sin(angle).toFloat()),
                        ),
                    )
                }
            }
            22 -> {
                val paper = Path().apply {
                    moveTo(w * .22f, h * .10f)
                    lineTo(w * .72f, h * .10f)
                    lineTo(w * .88f, h * .27f)
                    lineTo(w * .88f, h * .78f)
                    lineTo(w * .76f, h * .74f)
                    lineTo(w * .65f, h * .80f)
                    lineTo(w * .53f, h * .75f)
                    lineTo(w * .40f, h * .81f)
                    lineTo(w * .27f, h * .75f)
                    lineTo(w * .13f, h * .80f)
                    lineTo(w * .13f, h * .19f)
                    close()
                }
                drawPath(paper, gradient)
                val fold = Path().apply {
                    moveTo(w * .72f, h * .10f)
                    lineTo(w * .72f, h * .27f)
                    lineTo(w * .88f, h * .27f)
                    close()
                }
                drawPath(fold, Color(0xFFB99A68))
                for (i in 0..2) {
                    val y = h * (.43f + i * .12f)
                    drawLine(Color(0xFF9C835D).copy(alpha = .65f), Offset(w * .27f, y), Offset(w * .73f, y), w * .018f)
                }
                drawPath(paper, Color.White.copy(alpha = .65f), style = Stroke(width = w * .018f))
            }
            23 -> {
                drawPath(
                    Path().apply {
                        moveTo(w * .12f, h * .22f)
                        lineTo(w * .67f, h * .10f)
                        lineTo(w * .88f, h * .47f)
                        lineTo(w * .36f, h * .61f)
                        close()
                    },
                    Color(0xFFFF8A65),
                )
                drawPath(
                    Path().apply {
                        moveTo(w * .20f, h * .48f)
                        lineTo(w * .75f, h * .31f)
                        lineTo(w * .90f, h * .78f)
                        lineTo(w * .34f, h * .91f)
                        close()
                    },
                    Color(0xFF9575CD),
                )
                drawCircle(Color(0xFF4DB6AC), w * .19f, Offset(w * .69f, h * .68f))
                drawCircle(Color(0xFFFFD166), w * .075f, Offset(w * .28f, h * .31f))
                drawLine(Color.White.copy(alpha = .8f), Offset(w * .30f, h * .55f), Offset(w * .67f, h * .45f), w * .025f)
            }
        }
    }
}
