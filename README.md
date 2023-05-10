# Testcontainers Nedir?
Testcontainers, Java uygulamalarını hazırlamak ve test etmek için Docker tabanlı konteynerler oluşturan bir kütüphanedir. Bu sayede, uygulamaların tamamını farklı durumlar ve iş yükleri için kolayca test edebilirsiniz.

Sitesine göz atın: **[Testcontainers.org](https://www.testcontainers.org)**
# Testcontainers Kullanmanın Motivasyonu
Testcontainers kullanmanın en büyük motivasyonu, test ettiğiniz uygulamanın gerçek ortamını en yakından taklit edebilmenizdir. Bu sayede, uygulamanın nasıl davranacağını gerçek bir ortamda test etmek için gereken tüm bileşenleri sağlayabilirsiniz.

# Repodaki Klasörler
## ping-pong-server
Spring Boot ile oluşturulmuş küçük bir demo projesidir.
> - /ping endpointine yapılan isteğe pong cevabını döner
> - /pong endpointine yapılan isteğe ping cevabını döner

Makinanızda hali hazırda Docker yüklü ve çalışıyor olmalıdır. Daha sonra 
<code>mvn clean install -Pnative spring-boot:build-image</code>
ile bu proje native olarak derlemelisiniz. Bu komut Docker konteyneri oluşturup yerelinize kaydedecektir.
Kontrol etmek için <code>docker run --rm -p 3434:3434 ping-pong-server:0.0.1-SNAPSHOT</code> ile
konteyneri ayağa kaldırabilirsiniz.

## testcontainers-example
Bu projede PingPongClient isimli bir sınıf bulunmaktadır. Ping-pong sunucusunun 
ping yada pong endpointlerini çağırarak aldığı cevabı döner. Bu sınıf için Junit5 ile
birim testi yazılmış ve bu birim testin testcontainers yardımı ile ping-pong-server projesinin çıktısı olan 
docker konteyneri ayağa kaldırması sağlanmıştır. Bu birim testi,
Testcontainer'ın kullanımını basit bir şekilde örneklemektedir.

