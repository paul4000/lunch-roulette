/**
 * Created by Paulina on 14.01.2018.
 */
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
import {Link} from "react-router-dom";

class LoginPanel extends React.Component{

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.state = {
            showError: false
        };
    }

    handleSubmit(e) {
        e.preventDefault();
        var credentials = {};
        credentials["username"] = ReactDOM.findDOMNode(this.refs["username"]).value.trim();
        credentials["password"] = ReactDOM.findDOMNode(this.refs["password"]).value.trim();

        client({
            method: 'POST',
            path: '/users/login',
            entity: credentials,
            headers: {'Content-Type': 'application/json'}
        }).then(response => {
            console.log(response);
            if (response.status.code === 200) {
                this.props.history.push('/home');
            }
        }).catch(error => {
            this.setState({ showError: true });
        });

        console.log(credentials);
        // Navigate away from the dialog to hide it.
        // window.location = "#";
    }


    render() {

        return (
            <div className="container">
                <div className="row main">
                        <div className="panel-title text-center">
                            <h1 className="title">Lunch Roulette</h1>
                            <hr />
                        </div>
                </div>
                <div className="row">
                    <div className="main-login main-center">
                        <form className="form-horizontal" method="post" action="#">

                            <div className="form-group">
                                <label htmlFor="username" className="cols-sm-2 control-label">Nazwa użytkownika</label>
                                <div className="cols-sm-10">
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-users fa" aria-hidden="true"></i></span>
                                        <input type="text" className="form-control" name="username" ref="username"  placeholder="Wprowadź nazwę użytkownika..."/>
                                    </div>
                                </div>
                            </div>

                            <div className="form-group">
                                <label htmlFor="password" className="cols-sm-2 control-label">Hasło</label>
                                <div className="cols-sm-10">
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                        <input type="password" className="form-control" name="password" ref="password"  placeholder="Wprowadź haslo..."/>
                                    </div>
                                </div>
                            </div>

                            { this.state.showError ?
                                <p className="text-danger">Niepoprawne hasło lub nazwa użytkownika. </p> : null }

                            <div className="form-group ">
                                <button type="button" className="btn btn-primary btn-lg btn-block login-button" onClick={this.handleSubmit}>Zaloguj</button>
                            </div>
                            <div className="form-group ">
                                <button type="button" className="btn btn-outline-secondary btn-lg btn-block login-button"><Link to="/">Zarejestruj</Link></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

module.exports = {
    loginPanel: LoginPanel
};