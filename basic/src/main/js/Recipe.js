/**
 * Created by Paulina on 14.01.2018.
 */
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class Recipe extends React.Component {

    constructor(props) {
        super(props);
        this.sendEmail = this.sendEmail.bind(this);
        this.state = {
            showError: false,
            showSuccess: false
        };
    }

    sendEmail(e){

        var shoppingMessage = {
            recipeName: this.props.recipe.name,
            ingredients: this.props.recipe.ingredients
        };

        client({
            method: 'POST',
            path: '/messages/shoppingList',
            entity: shoppingMessage,
            headers: {'Content-Type': 'application/json'}
        }).then(response => {
            console.log(response);
            if (response.status.code === 200) {
                this.setState({
                    showSuccess: true,
                    showError: false
                });
            }
        }).catch(error => {
            this.setState({ showSuccess: false, showError:true });
        });

    }

    render() {

        var ingredients = this.props.recipe.ingredients.map((ingredient) =>
            <li>{ingredient.name}     {ingredient.quantity}</li>
        );

        var steps = this.props.recipe.steps.map((step) =>
            <li>{step.sequenceNumber}. {step.description}</li>
        );

        return (
            <div className="container w-75 ">
                <div className="d-flex w-100 justify-content-between">
                    <h1>{this.props.recipe.name}</h1>
                    <button type="button" className="btn btn-success" onClick={this.sendEmail}>Send me shopping list!</button>

                </div>
                <div className="list-group">
                    <a className="list-group-item list-group-item-action flex-column align-items-start active text-white">
                        <div className="d-flex w-100 justify-content-between">
                            <h5 className="mb-1">Ingredients</h5>

                        </div>
                        <ul>{ingredients}</ul>
                    </a>
                    <a className="list-group-item list-group-item-action flex-column align-items-start">
                        <div className="d-flex w-100 justify-content-between">
                            <h5 className="mb-1">Steps</h5>
                        </div>
                        <ul>{steps}</ul>
                    </a>
                </div>
                { this.state.showError ?
                    <div className="alert alert-dismissible alert-danger">
                        <button type="button" className="close" onClick={() => this.setState({ showError: false }) }></button>
                        <strong>Couldn't send email!</strong>
                    </div> : null }
                { this.state.showSuccess ?
                    <div className="alert alert-dismissible alert-success">
                        <button type="button" className="close" onClick={() => this.setState({ showSuccess: false }) }></button>
                        <strong>Shopping list successfully send!</strong>
                    </div> : null }
            </div>
        )

    }

}

module.exports = {
    recipe: Recipe
};