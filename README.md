# Retail Manager API

## Retail Manager Shop service built using Spring Boot


### A POST request used to insert New Shop

Example request

POST /shops
Content-Type: application/json;charset=UTF-8
Accept: application/json

```
{
  "shopName" : "Raymond Retail Shop",
  "shopAddress" : {
    "number" : "1248/A, East Street, Cawasvilla, Opposite Wonderland, Cawasvilla, Pune",
    "postCode" : "411001"
  }
}
```

Example Response

```
{
  "message" : "Successfully saved shop details",
  "shopName" : "Raymond Retail Shop",
  "shopAddress" : {
    "number" : "1248/A, East Street, Cawasvilla, Opposite Wonderland, Cawasvilla, Pune",
    "postCode" : "411001"
  }
}
```

Example response when shop details is replaced

```
json{
  "message" : "Current Version replaced Previous version successfully",
  "shopName" : "Raymond Retail Shop",
  "shopAddress" : {
    "number" : "1248/A, East Street, Cawasvilla, Opposite Wonderland, Pune",
    "postCode" : "411001"
  }
}

```

### A GET request to find nearest shop

Example request

GET /shops/lat/18.554994/lng/73.953224/nearest
Accept: application/json

Example Response

```
{
  "shopName" : "Superdry",
  "shopAddress" : {
    "number" : "Phoenix Market City, 20, Viman Nagar Rd, Clover Park, Viman Nagar, Pune",
    "postCode" : "411014"
  },
  "location" : {
    "latitude" : "18.5635752",
    "longitude" : "73.9176313"
  },
  "distance" : "6466 meters"
}
```

Example response when error url

```
{
  "status" : "error",
  "message" : "Requested url is not valid"
}
```



For detailed rest api documentation please build the project and goto /target/generated-docs/index.html