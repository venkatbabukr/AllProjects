function updateClock() {
    const now = new Date();

    let formattedTime = Intl.DateTimeFormat("en-us", {"hour12": true, "hour": "2-digit", "minute": "2-digit", "second": "2-digit"}).format(now);
    document.querySelector(".time").innerHTML = formattedTime;
    let formattedDate = Intl.DateTimeFormat("en-us", {"weekday": "long", "month": "long", "day": "2-digit", "year": "numeric"}).format(now);
    document.querySelector(".date-time").innerHTML = formattedDate;
}
updateClock();
setInterval(updateClock, 1000);