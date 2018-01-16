/**
 * Created by Paulina on 14.01.2018.
 */
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class Recipe extends React.Component {

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
                    <button type="button" className="btn btn-success">Send me shopping list!</button>

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
            </div>
        )

    }

}

module.exports = {
    recipe: Recipe
};