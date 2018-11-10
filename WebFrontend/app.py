from flask import Flask, render_template, redirect
import requests
from datetime import datetime
import dateparser

app = Flask(__name__)


@app.route('/')
def hello_world():
    return redirect('/sit')

@app.route('/sit')
def sits():
    data = requests.get('https://code-for-good.herokuapp.com/api/sit/getAll', json={}).json()
    return render_template('index.html', data=data, parse=dateparser.parse)

@app.route('/sit/add')
def sit_add():
    return render_template('sit-form.html')

@app.route('/sit/modify')
def sit_modify():
    return render_template('sit-form.html')

@app.route('/carer')
def carers():
    data = requests.get('https://code-for-good.herokuapp.com/api/user/getAll', json={}).json()
    print(data)
    return render_template('carers-list.html', data=data)

@app.route('/carer/add')
def carer_add():
    return render_template('carer-form.html')

@app.route('/carer/modify')
def carer_modify():
    return render_template('carer-form.html')

@app.route('/404')
def error404():
    return render_template('error-404.html')


if __name__ == '__main__':
    app.run()
