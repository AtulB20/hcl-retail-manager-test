# Retail Manager API

## Retail Manager Shop service built using Spring Boot


### A POST request used to insert New Shop

Example request

`POST /shops`
`Content-Type: application/json;charset=UTF-8`
`Accept: application/json`

`{`
  `"shopName" : "BigBasket",`
  `"shopAddress" : {`
  `"number" : "1123 443",`
  `"postCode" : "411 014"`
  `}`
`}`
`

Example Response

`{`
  `"shopName" : "Superdry",`
  `"location" : {`
    `"latitude" : 18.5635752,`
    `"longitude" : 73.9176313`
  `},`
  `"shopAddress" : {`
    `"number" : "Phoenix Market City, 20, Viman Nagar Rd, Clover Park, Viman Nagar, Pune",`
    `"postCode" : "411014"`
  `}`
`}`


### A GET request used to find nearest shop

Example request

`GET /shops/lat/18.554994/lng/73.953224/nearest`
`Accept: application/json`

Example Response

`{`
  `"shopName" : "Superdry",`
  `"location" : {`
    `"latitude" : 18.5635752,`
   `"longitude" : 73.9176313`
  `},`
  `"shopAddress" : {`
    `"number" : "Phoenix Market City, 20, Viman Nagar Rd, Clover Park, Viman Nagar, Pune",`
    `"postCode" : "411014"`
 `}`
`}`

For detailed rest api documentation please refer /target/generated-docs/index.html