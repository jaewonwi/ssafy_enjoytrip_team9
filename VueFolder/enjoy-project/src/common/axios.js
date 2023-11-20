import axios from 'axios'

// axios.create(): axios 객체 생성
export default axios.create({
  baseURL: 'http://localhost:8080',   // server url
  headers: {
    'Content-Type': 'application/json'
  },
  withCredentials: true,              // session을 사용하기 위함
})