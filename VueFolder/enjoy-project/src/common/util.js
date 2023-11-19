export default {
    makeDateStr : function(date, type){

      // console.log(date)
      // console.log(type)

      let tempMonth = (date.month < 10) ? `0${date.month}` : date.month
      let tempDay = (date.day < 10) ? `0${date.day}` : date.day
      return `${date.year}${type}${tempMonth}${type}${tempDay}`
    },
    makeTimeStr : function(time, type){
      let tempTime = (time.hour < 10) ? `0${time.hour}` : time.hour
      let tempMinute = (time.minute < 10) ? `0${time.minute}` : time.minute
      let tempSecond = (time.second < 10) ? `0${time.second}` : time.second
      return `${tempTime}${type}${tempMinute}${type}${tempSecond}`
    }
}