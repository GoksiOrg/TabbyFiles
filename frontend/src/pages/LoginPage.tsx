import { ChangeEvent, FormEvent, useEffect, useRef, useState } from 'react';
import validateInput from '../helpers/validateInput.ts';
import login from '../api/login.ts';
import { useSearchParams } from 'react-router-dom';
import DangerAlert from '../components/DangerAlert.tsx';

export default function LoginPage() {
    const [params, setParams] = useSearchParams();
    const [isSubmitting, setSubmitting] = useState<boolean>(false);
    const [loginError, setLoginError] = useState<boolean>(false);
    const usernameReference = useRef<HTMLInputElement>(null);
    const passwordReference = useRef<HTMLInputElement>(null);
    useEffect(() => {
        if (params.get('error') === 'true') {
            params.delete('error');
            setParams(params);
            setLoginError(true);
        } else setLoginError(false);
    }, []);
    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        event.target.classList.remove('is-invalid');
    };
    const performLoginAction = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const usernameCheck = validateInput(
            usernameReference.current!!,
            str => str.length >= 4 && str.length < 15
        );
        const passwordCheck = validateInput(passwordReference.current!!, str => str.length > 0); // TODO
        if (!usernameCheck || !passwordCheck) return;
        setSubmitting(true);
        login({
            username: usernameReference.current!!.value,
            password: passwordReference.current!!.value,
        })
            .then(() => {
                setSubmitting(false);
            })
            .catch(err => {
                console.error(err);
                setSubmitting(false);
            });
    };
    return (
        <form onSubmit={performLoginAction} noValidate>
            <div className='flex-center flex-column vh-100-wo-footer'>
                <div className='p-5 rounded-4 border border-tertary' style={{ backgroundColor: '#0C134F' }}>
                    <h2 className='mb-4'>Login to continue</h2>
                    <div className='form-floating mb-4'>
                        <input
                            id='usernameInput'
                            className='form-control'
                            placeholder='Goksi'
                            disabled={isSubmitting}
                            ref={usernameReference}
                            onChange={handleChange}
                            required
                        />
                        <label htmlFor='usernameInput' className='form-label text-tabby-secondary'>
                            Username
                        </label>
                        <div className='invalid-feedback'>Username must have at least 4 characters !</div>
                    </div>

                    <div className='form-floating mb-4 is-invalid'>
                        <input
                            id='passwordInput'
                            className='form-control'
                            type='password'
                            placeholder='tabby'
                            ref={passwordReference}
                            disabled={isSubmitting}
                            onChange={handleChange}
                            required
                        />
                        <label htmlFor='passwordInput' className='form-label text-tabby-secondary'>
                            Password
                        </label>
                        <div className='invalid-feedback'>Password doesn&apos;t meet the criteria !</div>
                    </div>
                    <DangerAlert shouldRender={loginError} message={'Invalid username or password !'} />
                    <button type='submit' className='btn btn-primary' disabled={isSubmitting}>
                        Login
                    </button>
                </div>
            </div>
        </form>
    );
}
