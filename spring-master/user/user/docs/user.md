# User API Spec

## Register User

EndPoint : POST /api/users

Request Body : 

```json
{
  "username" : "edo handoko",
  "password" : "12345678",
  "alamat" : "BSP, Mojokerto"
}
```

Response Body :

```json
{
  "metaData": {
    "code": 200,
    "message": "Berhasil menambahkan data"
  },
  "response": {
    "id": 1,
    "username" : "edo handoko",
    "password" : "12345678",
    "alamat" : "BSP, Mojokerto"
  }
}
```