# retrofit-converter-jsoup

Provides a <a href="https://github.com/square/retrofitr">retrofit</a> converter implemented with <a href="https://github.com/btheu/JSoupMapper">JSoupMapper</a>

### Gettings Started

This short example demonstrate how to grab the statistics from a google query in plain HTML.
(without using REST/JSON api)

The last version of ```retrofit-converter-jsoup``` is using retrofit 2.x.
If your project deals with retrofit 1.x, please jump to corresponding paragraph below.

#### Create Retrofit API Adapter

```java
import btheu.retrofit2.jsoup.converter.JSoupConverterFactory;
...

Retrofit retrofit = new Retrofit.Builder()
	.baseUrl("https://www.google.com/")
	.addConverterFactory(JSoupConverterFactory.create())
	.build();

GoogleApi googleApi = retrofit.create(GoogleApi.class);

String stats = googleApi.search("retrofit").execute().body()
        .getResultStatistics();

```
#### Create Interface API
```java
import btheu.retrofit2.jsoup.JSoup;

public static interface GoogleApi {

	// @JSoup is mandatory if GET returns plain HTML and
	// Page have to be mapped from it with JSoup
	@JSoup 
	@GET("/search?hl=en&safe=off")
	@Headers({ "User-Agent:Mozilla" })
	public Call<Page> search(@Query("q") String query);

}
```

```java
@Data
public static class Page {

	@JSoupSelect("#resultStats")
	@JSoupText
	public String resultStatistics;

}

```

### Download the converter

```xml
<dependency>
	<groupId>com.github.btheu.jsoupmapper</groupId>
	<artifactId>retrofit-converter-jsoup</artifactId>
	<version>0.1.0</version>
</dependency>
```

# Using with Retrofit 1.x

```java
import btheu.retrofit.converter.jsoup.JSoupConverter
...

RestAdapter build = new RestAdapter.Builder()
	.setEndpoint("https://website/")
	.setConverter(new JSoupConverter())
	.build();

```

### Download the converter

```xml
<dependency>
	<groupId>com.github.btheu.jsoupmapper</groupId>
	<artifactId>retrofit-converter-jsoup</artifactId>
	<version>0.1.0</version>
</dependency>
```
