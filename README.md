# Tutorial APAP

## Authors

* **<Sulthan Zahran>** - *<1806191585>* - *<B>*

---
## Tutorial 1
### What I have learned today
### Github
1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?
 Issue tracker adalah sebuah fitur pada github yang dapat digunakan untuk mengatur dan memantau 
 tugas, perbaikan atau bugs yang ada pada project kita. Dengan adanya Issue kita dapat mengatur
 pengerjaan suatu tugas seperti menentukan apa yang dikerjakan, siapa yang mengerjakan/ kepada
 siapa dikerjakan, menentukan label kategorisasi pekerjaan, dan melihat milestone dari issue
 tersebut.
 
2. Apa perbedaan dari git merge dan git merge --squash?
Ketika melakukan git merge, semua history/log commit pada brach yang di-merge akan digabung ke log
commit yang berada di master sementara ketika melakukan git merge -- squash, semua log commit pada
branch akan dijadikan satu ketika di-merge ke branch master namun tidak menghilangkan history/
log commit yang berada di branch.

3. Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan suatu
aplikasi?
Version control dapat memudahkan kita dalam pengerjaan sebuah aplikasi terlebih lagi jika dikerjakan
dalam sekelompok orang. Dengan version control kita dapat membagi pekerjaan secara mudah. Seorang
programmer bisa mengerjakan bagiannya dalam sebuah aplikasi tanpa terganggu oleh pekerjaan
programmer lainnya. Dengan version control, kita juga dimudahkan dengan pengumpulan pekerjaan dan
pengontrolan pekerjaan tersebut. Kita bisa mengetahui siapa saja yang mengubah sebuah code sehingga
dapat memudahkan pelacakan jika terjadi sebuah error.

### Spring
4. Apa itu library & dependency?
Library adalah sekumpulan code yang digabung/di-bundle menjadi satu dan dapat di import/digunakan
ke code lain. Library memudahkan karena kita tidak perlu lagi bersusah payah membuat fungsi. Kita
dapat mengimport sebuah fungsi dari library asal kita tahu penggunaan dari library tersebut.
Dependency adalah ketergantungan. Dalam dunia programming, suatu program mungkin membutuhkan program
lainnya untuk berjalan. Ada banyak file yang membutuhkan module-module agar file tersebut dapat
berjalan. Module-module tersebut disebut dependencies.

5. Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven?
Maven adalah salah satu build tools yang menggunakan Project Object Model (POM). Dengan adanya 
build tools ini, kita bisa mengerjakan project di berbagai IDE karena maven dapat membuat struktur
project tersebut sendiri. Maven juga dapat memudahkan kita dalam mengatur dependency. Kita hanya
perlu mendefinisikannya di POM.xml dan maven akan mendownload file dependency tersbut ke repositori
sehingga dapat digunakan. Ada beberapa build tools populer selain maven seperti boot, gradle, 
MsBuild, dan lain-lain

6. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring framework?
Spring framework dapat digunakan untuk memudahkan kita dalam membuat program yang minim akan
komponen-komponen yang memiliki ketergantungan satu sama lain. Spring framework juga memudahkan
developer dalam melakukan manajemen transaksi. Selain itu Spring framework 

7. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya menggunakan
@RequestParam atau @PathVariable?
@RequestParam menerima parameter sebuah method dari query dari sebuah string sementara @PathVariable
menerima parameter untuk method dari URI path. @PathVariable lebih baik digunakan ketika parameter
permintaan bersifat dinamis. Hal ini dilakukan karena @RequestParam melakuan encode dari value
yang diterima, sementara @PathVariable tidak.

### What I did not understand
(tuliskan apa saja yang kurang Anda mengerti, Anda dapat men-_check_ apabila Anda sudah mengerti
dikemudian hari, dan tambahkan tulisan yang membuat Anda mengerti)
- [ ] Kenapa saya harus belajar APAP?
- [x] Kenapa?
 Karena …
(Anda dapat membuat tampilan code dalam README.md menjadi lebih baik. Cari tahu lebih dalam
tentang penulisan README.md di GitHub pada link
[berikut](https://help.github.com/en/articles/basic-writing-and-formatting-syntax))

## Tutorial 2
Pertanyaan 1: Cobalah untuk menambahkan sebuah resep dengan mengakses link berikut:
http://localhost:8080/resep/add?noResep=1&namaDokter=Papa%20APAP&namaPasien=Quanta%20F
asilkom&catatan=Semangat
Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi

Jawab:
Pada halaman localhost:8080 akan muncul error Whitelable Error Page. Hal ini terjadi karena kita
belum membuat template/view tempat kita menampilkan halamannya.

Pertanyaan 2: Menurut kamu anotasi @Autowired pada class Controller tersebut merupakan
implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja @Autowired tersebut dalam
konteks service dan controller yang telah kamu buat

Jawab:
Anotasi @Autowired yang digunakan pada class Controller merupakan implementasi dari konsep dependency
injection. Dengan anotasi ini, kita tidak perlu untuk membuat constructor dan setter pada attribut
class yang menggunakan interface tersebut selama ada bean/class lain yang meng-implement interface
tersebut. Anotasi ini akan meng-inject constructor dan setter dari class lain yang meng-implement
interface tersebut.

Pertanyaan 3: Cobalah untuk menambahkan sebuah resep dengan mengakses link berikut:
http://localhost:8080/resep/add?noResep=1&namaDokter=Papa%20APAP&namaPasien=Quanta%20F
asilkom Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.

Jawab:
Pada halaman localhost:8080 akan muncul error Whitelable Error Page. Hal ini terjadi karena kita
tidak menambahkan variable "catatan" pada path. Pada fungsi Controller yang memetakan ke halaman 
/resep/add , kita memerlukan 4 variable dari path untuk menampilkan halaman tersebut, yaitu : 
"noResep", "namaDokter", "namaPasien", dan "catatan".

Pertanyaan 4: Jika Papa APAP ingin melihat resep untuk pasien yang bernama Quanta, link apa yang
harus diakses?

Jawab:
Saya tidak bisa menemukan fungsi yang dapat mencari resep berdasarkan nama pada tutorial yang
diberikan kecuali jika diperintahkan untuk membuat fungsi baru. Untuk saat ini, pencarian dengan
nama bisa dilakukan secara manual dengan mengakses link [ini](http://localhost:8080/resep/viewall)

Pertanyaan 5: Tambahkan 1 contoh resep lainnya sesukamu. Lalu cobalah untuk mengakses
http://localhost:8080/resep/viewall , apa yang akan ditampilkan? Sertakan juga bukti screenshotmu.

Jawab:
Page tersebut akan menampilkan semua resep yang telah dibuat dan semua attribut dari resep, yaitu:
noResep, namaDokter, namaPasien, dan catatan.
![](images/no5.jpg)

