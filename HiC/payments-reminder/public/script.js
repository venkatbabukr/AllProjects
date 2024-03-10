document.addEventListener("DOMContentLoaded", (event) => {
    loadReminders();
});

async function loadReminders() {
    try {
        const response = await fetch('/getAllReminder');
        const reminders = await response.json();

        let remindersListDiv = document.getElementById("reminderList");
        remindersListDiv.innerHTML = '<h2>Reminder List</h2>';
        for (const reminder of reminders) {
            let reminderPara = document.createElement('p');
            reminderPara.innerHTML = `<p>${reminder.reminderMsg} - ${new Date(reminder.remindAt).toLocaleString()}</p>`;
            remindersListDiv.appendChild(reminderPara);
        }
    } catch (err) {
        console.log("loadReminders encountered error!" + err);
    }
}

function validateNewReminderInputsAndGetPayload(reminderMsg, remindAt) {
    if (!reminderMsg) {
        throw "Please enter a reminder message";
    }
    let reminderDate = new Date(remindAt);
    if (isNaN(reminderDate)) {
        throw "Please enter a reminder date";
    }
    if (reminderDate < new Date()) {
        throw "Please choose a future date and time";
    }
    return {
        "reminderMsg": reminderMsg,
        "remindAt": reminderDate
    };
}

function addReminder() {
    let reminderMsg = document.querySelector("#reminderMsg").value;
    let remindAt = document.querySelector("#remindAt").value;
    try {
        let newReminderPayload = validateNewReminderInputsAndGetPayload(reminderMsg, remindAt);
        console.log(newReminderPayload);
        fetch("/addReminder", {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Origin": "*"
            },
            body: JSON.stringify(newReminderPayload)
        }).then(response => {
            console.log(response.json());
            if (response.status === 200) {
                console.log("addReminder added reminder!");
                loadReminders();
            }
        }).catch(err => {
            console.error("Got error while adding reminder!", err);
        });
    } catch (err) {
        alert(err);
    }
}