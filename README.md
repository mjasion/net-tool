#http-api [![Build Status](https://travis-ci.org/mjasion/filmweb-rss.svg?branch=master)](https://travis-ci.org/mjasion/filmweb-rss)

####GET /ip - https://httpapi.herokuapp.com/ip
``` 
marcin@Dell:~$ curl -X GET https://httpapi.herokuapp.com/ip
{
  "ip" : "176.102.189.23"
}
```

####GET /headers - http://httpapi.herokuapp.com/headers
```
marcin@Dell:~$ curl -X GET https://httpapi.herokuapp.com/headers
{
  "accept" : "*/*",
  "connection" : "close",
  "host" : "httpapi.herokuapp.com",
  "user-agent" : "curl/7.35.0",
  "x-forwarded-port" : "80",
  "x-forwarded-proto" : "http",
  "x-request-id" : "3dc86327-164b-4901-be00-9607a562f906",
  "x-request-start" : "1403966155914"
}
```

####GET /params- https://httpapi.herokuapp.com/params
```
marcin@Dell:~$ curl -X GET "https://httpapi.herokuapp.com/params?key1=val1_1,val_1_2&key2=val2_1&key2=val_2_2"
{
  "method": "GET",
  "params": [
    {
      "key": "key1",
      "value": "val1_1,val_1_2"
    },
    {
      "key": "key2",
      "value": [
        "val2_1",
        "val_2_2"
      ]
    }
  ]
}
```

####POST /params - https://httpapi.herokuapp.com/params
```
marcin@Dell:~$curl -X POST -d "key1=val1_1,val_1_2&key2=val2_1&kwy2=val_2_2" "https://httpapi.herokuapp.com/params"
{
  "method": "POST",
  "params": [
    {
      "key": "key1",
      "value": "val1_1,val_1_2"
    },
    {
      "key": "key2",
      "value": "val2_1"
    },
    {
      "key": "kwy2",
      "value": "val_2_2"
    }
  ]
}
```
