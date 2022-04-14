import {Fragment} from "react";
import {Container, Navbar} from "react-bootstrap";
import {Route} from "react-router-dom";
import React from "react";

function NavRoute({...props}) {
    return (
        <Fragment>
            <Navbar bg="light">
                <Container>
                    <Navbar.Brand href="/">Home</Navbar.Brand>
                </Container>
            </Navbar>
            <main role="main">
                <Route {...props} />
            </main>
        </Fragment>
    )
}

export default NavRoute;