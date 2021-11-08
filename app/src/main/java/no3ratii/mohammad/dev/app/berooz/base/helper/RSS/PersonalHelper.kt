package no3ratii.mohammad.dev.app.berooz.base.helper.RSS

import no3ratii.mohammad.dev.app.berooz.R

/**
 *
 */
class PersonalHelper{
    companion object {
        /**
         * set date background by month name in activity main and rssAdapter first item
         */
        fun dateBackgrond(monthNumber: Int): Int {
            return when (monthNumber) {
                1 -> R.drawable.spring_1
                2 -> R.drawable.spring_2
                3 -> R.drawable.spring_3
                4 -> R.drawable.summery_1
                5 -> R.drawable.summery_2
                6 -> R.drawable.summery_3
                7 -> R.drawable.fall_1
                8 -> R.drawable.fall_2
                9 -> R.drawable.fall_3
                10 -> R.drawable.winter_2
                11 -> R.drawable.winter_1
                12 -> R.drawable.winter_3
                else -> R.drawable.defult
            }
        }

        /**
         *  set category nackgrond in list items set by categord name
         */
        fun categoryBackgrond(category: String): Int {
            return when (category) {
                "بازی" -> R.drawable.category_red_bg
                "فناوری" -> R.drawable.category_pink_bg
                "موبايل" -> R.drawable.category_purple_bg
                "روباتيك" -> R.drawable.category_deep_purple_bg
                "سخت افزار" -> R.drawable.category_deep_purple_bg
                "اينترنت" -> R.drawable.category_indigo_bg
                "شبكه اجتماعی" -> R.drawable.category_blue_bg
                "کسب و کار" -> R.drawable.category_light_blue_bg
                "دانش بنیان" -> R.drawable.category_cyan_bg
                "فرصت های شغلی" -> R.drawable.category_teal_bg
                "شبكه و امنيت" -> R.drawable.category_light_green_bg
                "ارتباطات" -> R.drawable.category_lime_bg
                "ماهواره و فضا" -> R.drawable.category_yellow_bg
                "برنامه نويسی" -> R.drawable.category_orange_bg
                "مديريت ICT" -> R.drawable.category_deep_orange_bg
                "نرم افزار" -> R.drawable.category_green_bg
                "اپراتورها" -> R.drawable.category_deep_green_bg
                "وب و اينترنت" -> R.drawable.category_black_bg
                else -> R.drawable.category_bg
            }
        }
    }
}