import React from 'react';

function InputField({
                        field,
                        label,
                        form,
                        maxDate,
                        value,
                        className,
                        ...props
                    }) {

    return (
        <div className="form-row">
            <div className={className}>
                <div className="form-group">
                    <label>{label}</label>
                    <input
                        {...field}
                        {...props}
                        type="text"
                        className="form-control"
                    />
                    <span>
                        {form.touched[field.name] && form.errors[field.name] && (
                            <span>{form.errors[field.name]}</span>
                        )}
                      </span>
                </div>
            </div>
        </div>
    );
}

export default InputField;