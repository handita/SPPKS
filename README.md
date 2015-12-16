# SPPKS
Source Code Contoh Aplikasi Aplikasi Survei Kuesioner berbasis Android

##Aplikasi Survei Berbasis Android Tablet

Created By [Handita Okviyanto](https://www.linkedin.com/in/handita-okviyanto-71387133)


Detil posting dapat dilihat di halaman ini
http://lawangkode.com/cara-membuat-aplikasi-survei-dengan-kuesioner-berbasis-tablet-android/

Password default :
- username :admin
- password :nimda

Teknologi yang dipakai

1. Sqllite untuk penyimpanan data menggunakan activedroid
2. Webservice menggunakan yii framework
3. Database server menggunakan sql server
4. RestTemplate library spring framework

##Tampilan Splash Screen 


![SplashScreen](https://raw.githubusercontent.com/handita/SPPKS/master/screenshot/sppks2.png)


##Tampilan Login Screen


![Login Screen](https://raw.githubusercontent.com/handita/SPPKS/master/screenshot/sppks3.jpg)

##Tampilan Home Screen


![Home Screen](https://raw.githubusercontent.com/handita/SPPKS/master/screenshot/sppks4.jpg)

##Tampilan Sinkron Petugas


![Sinkron Petugas](https://raw.githubusercontent.com/handita/SPPKS/master/screenshot/sppks5.jpg)

##Tampilan Daftar Sampel dengan ListView


![ListView](https://raw.githubusercontent.com/handita/SPPKS/master/screenshot/sppks6.jpg)

##Tampilan Kuesioner 


![Kuesioner](https://raw.githubusercontent.com/handita/SPPKS/master/screenshot/sppks7.jpg)

##Tampilan Kuesioner dalam bentuk Tabel

![Tabel](https://raw.githubusercontent.com/handita/SPPKS/master/screenshot/sppks8.jpg)

# ServicesSPPKS
Web services SPPKS menggunakan Yii Framework berbasis REST di folder **Services**

Web services ini dipakai untuk aplikasi Android [SPPKS](https://github.com/handita/SPPKS)

Service yang dipakai di `ApiController.php`

Data yang dipakai adalah sql server dengan database di dalam file **SPPKS2015.rar**

Data yang dikeluarkan dalam bentuk `json` diantaranya ada data `prov`,`kab`,`kec`,`desa`,`bs`,`mnusrt`,`petugas` 

Service url menggunakan

    POST Ke
    `/api/mnusrt`
    data : token,kode_petugas


