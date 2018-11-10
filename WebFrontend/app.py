from flask import Flask, render_template, redirect, url_for, request
import requests
import json
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

@app.route('/carer/add', methods=['GET', 'POST'])
def carer_add():
    if request.method == 'POST':
        username = request.form['usernameinput']
        name = request.form['nameinput']
        defpassword = request.form['defaultpasswordinput']
        data = {}
        data['name'] = name
        data['username'] = username
        data['password'] = defpassword
        json_data = json.dumps(data)
        print(json_data)
        headers = {
            'Content-Type': "application/json",
        }
        response = requests.request("POST", "https://code-for-good.herokuapp.com/api/user/add-user",
                                                            data=json_data, headers=headers)
        return redirect('/carer')
    else:
        return render_template('carer-form.html')

@app.route('/carer/modify')
def carer_modify():
    return render_template('carer-form.html')

@app.route('/404')
def error404():
    return render_template('error-404.html')


if __name__ == '__main__':
    app.run()
