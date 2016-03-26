# retrofit-converter-jsoup

Provides a <a href="https://github.com/square/retrofitr">retrofit</a> converter for <a href="https://github.com/btheu/JSoupMapper">JSoupMapper</a>

### Gettings Started

```java

import btheu.retrofit.converter.jsoup.JSoupConverter
...

RestAdapter build = new RestAdapter.Builder()
	.setEndpoint("https://website/")
	.setConverter(new JSoupConverter())
	.build();

```

### Download

Download via Maven:

```xml
<dependency>
	<groupId>com.github.btheu.jsoupmapper</groupId>
	<artifactId>retrofit-converter-jsoup</artifactId>
	<version>0.1.0</version>
</dependency>
```

# Licence


