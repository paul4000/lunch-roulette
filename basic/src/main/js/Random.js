/**
 * Created by Paulina on 14.01.2018.
 */

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
const Recipe = require('./Recipe').recipe;

class Random extends React.Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.state = {
            gotRecipe: false,
            showSomething: false,
            recipe: {}
        };
    }

    handleSubmit(e) {
        e.preventDefault();

        client({
            method: 'GET',
            path: '/recipes/random',
            headers: {'Content-Type': 'application/json'}
        }).then(response => {
            console.log(response);
            if (response.status.code === 200) {
                this.setState({
                    recipe: response.entity,
                    showSomething: true,
                    gotRecipe: true
                });
            }
        }).catch(error => {
            this.setState({ showSomething: true, gotRecipe:false });
        });

    }

    render() {

        return (
        <div>
            <div className="container">
                    <div className="row center-button-container">
                        <button className="btn btn-primary btn-danger center-block" onClick={this.handleSubmit}>WYLOSUJ OBIAD!</button>
                    </div>
            </div>

        { this.state.showSomething ? (this.state.gotRecipe ?  <Recipe recipe={this.state.recipe}/> : <div>Nie masz żadnych przepisów :( </div>) : null}

        </div>
        )
    }

}

module.exports = {
    random: Random
};