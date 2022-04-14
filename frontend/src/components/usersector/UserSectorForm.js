import {Field, Form, Formik} from "formik";
import InputField from "../form/InputField";
import {Button, Container, Row} from "react-bootstrap";
import SelectField from "../form/SelectField";
import CheckBoxField from "../form/CheckBoxField";
import React from "react";
import {createUserSector, updateUserSector} from "../../api/UserSectorApi";
import {NotificationManager} from "react-notifications";
import {parseRoots} from "../../helpers/sectors_helper";

function UserSectorForm({session, rootSectors}) {

    const [userSector, setUserSector] = React.useState();

    const initialValues = {
        username: "",
        agreedToTerms: true,
        sectors: []
    };

    async function handleFormSubmit(values, formHelpers) {
        if (session) {
            if (userSector) {
                updateUserSector(session.id, {id: userSector.id, ...values})
                    .then(resp => {
                        setUserSector(resp)
                        NotificationManager.success("Data updated", "Success", 4000)
                    })
                    .catch(() => NotificationManager.error("An error occurred while updating data", "Error", 4000))
            } else {
                createUserSector(session.id, values)
                    .then(resp => {
                        setUserSector(resp)
                        NotificationManager.success("Data saved", "Success", 4000)
                    })
                    .catch(() => NotificationManager.error("An error occurred while saving data", "Error", 4000))
            }
        }
    }

    return (
        <Formik
            initialValues={{...initialValues}}
            onSubmit={handleFormSubmit}>
            {({setFieldValue, values}) => (
                <Form className="form">
                    <Container>
                        <Row>
                            <h6>Please enter your name and pick the Sectors you are currently involved in.</h6>
                        </Row>
                        <Row>
                            <Field
                                component={InputField}
                                name="username"
                                label="Name:"
                                placeholder="Input your name"
                                className="col col-md-12"
                                required
                                onChange={(event) => {
                                    setFieldValue('username', event.target.value);
                                }}
                            />
                        </Row>
                        <Row>
                            <Field
                                component={SelectField}
                                name="sectors"
                                label="Sectors:"
                                data={parseRoots(rootSectors)}
                                className="col col-md-12"
                                required={true}
                                multiple={true}
                                onChange={(selectedOptions) => {
                                    setFieldValue('sectors', selectedOptions);
                                }}
                            />
                        </Row>
                        <Row>
                            <Field
                                component={CheckBoxField}
                                name="agreedToTerms"
                                label="Agree to terms"
                                className="col col-md-12"
                                defaultChecked={initialValues.agreedToTerms}
                                onChange={() => {
                                    setFieldValue('agreedToTerms', !values.agreedToTerms)
                                }}
                            />
                        </Row>
                        <Row>
                            <Button type="submit" variant="primary" size="sm">Save</Button>
                        </Row>
                    </Container>
                </Form>
            )}
        < /Formik>
    )
}

export default UserSectorForm;