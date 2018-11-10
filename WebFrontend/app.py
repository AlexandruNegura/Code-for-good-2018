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


@app.route('/sit/add', methods=['GET', 'POST'])
def sit_add():
    if request.method == 'POST':
        patientId = request.form['patientId']
        date = request.form['dateinput']
        startdate = request.form['starttimeinput']
        enddate = request.form['endtimeinput']
        carer = list()
        carer.append(int(request.form['carer_1_input']))
        carer.append(int(request.form['carer_2_input']))
        carer.append(int(request.form['carer_3_input']))
        carer.append(int(request.form['carer_4_input']))
        carer.append(int(request.form['carer_5_input']))
        carer.append(int(request.form['carer_6_input']))

        startdate = datetime.strptime(date+startdate, '%Y-%m-%d%I:%M')
        enddate = datetime.strptime(date+enddate, '%Y-%m-%d%I:%M')
        data = {}
        data['startDate'] = startdate.strftime("%Y-%m-%dT%H:%M:%S.%f")
        data['endDate'] = enddate.strftime("%Y-%m-%dT%H:%M:%S.%f")
        data['patientId'] = patientId
        data['canceled'] = False
        data['carrersIds'] = [element for element in carer if element != 0]

        json_data = json.dumps(data)
        print(json_data)
        headers = {
            'Content-Type': "application/json",
        }
        response = requests.request("POST", "https://code-for-good.herokuapp.com/api/sit/add-sit",
                                    data=json_data, headers=headers)

        return redirect('/sit')
    else:
        carers = requests.get('https://code-for-good.herokuapp.com/api/user/getAll', json={}).json()
        return render_template('sit-form.html', carers=carers)


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


@app.route('/carer/alert')
def carers_missing():
    data = requests.get('https://code-for-good.herokuapp.com/api/user/get-all-not-home-safe', json={}).json()
    print(data)
    return render_template('carers-list.html', data=data)


@app.route('/carer/modify')
def carer_modify():
    return render_template('carer-form.html')


@app.route('/404')
def error404():
    return render_template('error-404.html')


if __name__ == '__main__':
    app.run()