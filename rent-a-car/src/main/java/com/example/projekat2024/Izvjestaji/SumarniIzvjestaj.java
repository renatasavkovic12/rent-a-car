package com.example.projekat2024.Izvjestaji;
/**
 * Klasa koja predstavlja SUMARNI IZVJESTAJ
 * @author Renata Savkovic
 * @version 1.0
 */
public class SumarniIzvjestaj extends DnevniIzvjestaj{
        /**
         * informacija o ukupnim troskovima kompanije
         */
        public double ukupniTroskoviKompanije;
        /**
         * informacija o ukupnom porezu kompanije
         */
        public double ukupanPorezKompanije;
        /**
         * konstruktor klase SumarniIzvjestaj
         */
        public SumarniIzvjestaj(){}

        public double getUkupniTroskoviKompanije() {
                return ukupniTroskoviKompanije;
        }

        public void setUkupniTroskoviKompanije(double ukupniTroskoviKompanije) {
                this.ukupniTroskoviKompanije = ukupniTroskoviKompanije;
        }

        public double getUkupanPorezKompanije() {
                return ukupanPorezKompanije;
        }

        public void setUkupanPorezKompanije(double ukupanPorezKompanije) {
                this.ukupanPorezKompanije = ukupanPorezKompanije;
        }
}
