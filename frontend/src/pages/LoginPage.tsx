export default function LoginPage() {
    return (
        <form>
            <div className="flex-center flex-column vh-100-wo-footer">
                <div className="p-5 rounded-4 border border-tertary" style={{backgroundColor: "#0C134F"}}>
                    <h2 className="mb-4">Login to continue</h2>
                    <div className="form-floating mb-4">
                        <input id="usernameInput" className="form-control" placeholder="Goksi"/>
                        <label htmlFor="usernameInput" className="form-label text-tabby-secondary">Username</label>
                        <div className="invalid-feedback">
                            Username must have at least 4 characters !
                        </div>
                    </div>

                    <div className="form-floating mb-4 is-invalid">
                        <input id="passwordInput" className="form-control" type="password" placeholder="maca"/>
                        <label htmlFor="passwordInput" className="form-label text-tabby-secondary">Password</label>
                        <div className="invalid-feedback">
                            Password doesn't meet the criteria !
                        </div>
                    </div>

                    <button type="submit" className="btn btn-primary">Login</button>
                </div>
            </div>
        </form>
    )
}