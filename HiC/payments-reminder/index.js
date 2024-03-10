const express = require("express");
const cors = require("cors");
const bodyParser = require("body-parser");
const mysql = require("mysql2");
const dotenv = require("dotenv");
const nodemailer = require("nodemailer");
const cron = require("node-cron");
const path = require('path');

dotenv.config();

const app = express();
const PORT = process.env.PORT1;

app.use(cors());
app.use(bodyParser.json());

const db = mysql.createConnection({
  host: process.env.DB_HOST,
  user: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_DATABASE,
});

var transport = nodemailer.createTransport({
  host: "sandbox.smtp.mailtrap.io",
  port: 2525,
  auth: {
    user: process.env.EMAIL_USERNAME,
    pass: process.env.EMAIL_PASSWORD
  }
});




db.connect((err) => {
  if (err) {
    console.error("Error connecting to MySQL:", err);
  } else {
    console.log("Connected to MySQL");
    createTable();
    setupEmailReminderJob();

    app.use(express.static(path.join(__dirname, 'public')));
  }
});

function createTable() {
  const createSQL = `CREATE TABLE IF NOT EXISTS reminders
  (
    id int NOT NULL AUTO_INCREMENT,
    reminderMsg varchar(255) NOT NULL,
    remindAt datetime NOT NULL,
    isReminded tinyint(1) DEFAULT '0',
    PRIMARY KEY (id)
  )`;
  db.query(createSQL, (err) => {
    if (err) {
      console.error("Error executing SQL query:", err);
    }
    console.log("Table created or already exists")
 });
}

app.post("/addReminder", (req, res) => {
  console.log("Got payload: ", req.body);
  let {
    reminderMsg,
    remindAt
  } = req.body;
  const insertSQL = `INSERT INTO
  reminders(reminderMsg, remindAt)
  VALUES (?, ?)`;
  db.query(insertSQL, [reminderMsg, new Date(remindAt)], (err, result) => {
    if (err) {
      console.error("Error executing Insert: ", err);
      res.status(500).send({
        "error": "addReminder encountered internal error. Contact admin if problem persists!"
      });
    } else {
      console.log("Reminder added successfully with id: ", result.insertId);
      res.send({
        "success": true,
        reminder: {
          "id": result.insertId,
          "reminderMsg": reminderMsg,
          "remindAt": remindAt,
          "isReminded": false
        }
      });
    }
  });
});

app.get("/getAllReminder", (req, res) => {
  db.query("SELECT * FROM reminders", (err, result) => {
    if (err) {
      console.error("Error executing Slect query: ", err);
      res.status(500).send({
        "error": "getAllReminder encountered internal error. Contact admin if problem persists!"
      });
    } else {
      console.log("getAllReminders returning reminders: " + result.length);
      res.send(result);
    }
  });
});

function setupEmailReminderJob() {
  cron.schedule("* * * * *", () => {
    sendReminders();
  });
}

function sendReminders() {
  const remindersQuery = "SELECT * FROM reminders WHERE isReminded = 0 AND remindAt <= NOW()";
  db.query(remindersQuery, (err, result) => {
    if (err) {
      console.error("sendReminders encountered DB error when querying for reminders!", err);
    } else {
      result.forEach(reminder => sendEmailReminder(reminder));
    }
  });
}

function sendEmailReminder(reminder) {
  let emailText = `Payment reminder: ${reminder.reminderMsg} - Due at ${new Date(reminder.remindAt).toLocaleString()}`;
  const mailOptions = {
    "from": "hicounselor@gmail.com",
    "to": "Mailtrap Inbox <to@example.com>",
    "subject": "Node Mailer",
    "text": emailText
  };
  transport.sendMail(mailOptions, (err, info) => {
    if (err) {
      console.error("Sending reminder email failed with error!", err);
    } else {
      console.log("Email sent, ", info.response);
      markReminderAsSent(reminder.id);
    }
  });
}

function markReminderAsSent(reminderId) {
  const updateStmt = "UPDATE reminders set isReminded = 1 WHERE id = ?"
  db.query(updateStmt, [reminderId], (err, result) => {
    if (err) {
      console.error("Mark reminder as sent failed with error: ", err);
    } else {
      console.log("Reminder marked as sent for id: ", reminderId);
    }
  });
}

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});