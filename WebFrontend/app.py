from flask import Flask, render_template

app = Flask(__name__)


@app.route('/')
def hello_world():
    return render_template('index.html')

@app.route('/sit/add')
def sit_add():
    return render_template('sit-form.html')

@app.route('/carer/add')
def carer_add():
    return render_template('carer-form.html')


if __name__ == '__main__':
    app.run()
