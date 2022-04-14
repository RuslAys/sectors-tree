import React from 'react';

function CheckBoxField({field, label, value, className, ...props}) {
    return (
        <div className="form-group">
            <div className="form-row">
                <div className={className}>
                    <div className="form-check">
                        <input
                            {...field}
                            {...props}
                            className="form-check-input"
                            type="checkbox"/>
                        <label>{label} </label>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default CheckBoxField;