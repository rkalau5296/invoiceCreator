var _a;
export var ConnectionState;
(function (ConnectionState) {
    /**
     * Application is connected to server: last transaction over the wire (XHR /
     * heartbeat / endpoint call) was successful.
     */
    ConnectionState["CONNECTED"] = "connected";
    /**
     * Application is connected and Flow is loading application state from the
     * server, or Fusion is waiting for an endpoint call to return.
     */
    ConnectionState["LOADING"] = "loading";
    /**
     * Application has been temporarily disconnected from the server because the
     * last transaction over the wire (XHR / heartbeat / endpoint call) resulted
     * in a network error, or the browser has received the 'online' event and needs
     * to verify reconnection with the server. Flow is attempting to reconnect
     * a configurable number of times before giving up.
     */
    ConnectionState["RECONNECTING"] = "reconnecting";
    /**
     * Application has been permanently disconnected due to browser receiving the
     * 'offline' event, or the server not being reached after a number of reconnect
     * attempts.
     */
    ConnectionState["CONNECTION_LOST"] = "connection-lost";
})(ConnectionState || (ConnectionState = {}));
export class ConnectionStateStore {
    constructor(initialState) {
        this.stateChangeListeners = new Set();
        this.loadingCount = 0;
        this.connectionState = initialState;
        this.serviceWorkerMessageListener = this.serviceWorkerMessageListener.bind(this);
        if (navigator.serviceWorker) {
            // Query service worker if the most recent fetch was served from cache
            // Add message listener for handling response
            navigator.serviceWorker.addEventListener('message', this.serviceWorkerMessageListener);
            // Send JSON-RPC request to Vaadin service worker
            navigator.serviceWorker.ready.then((registration) => {
                var _a;
                (_a = registration === null || registration === void 0 ? void 0 : registration.active) === null || _a === void 0 ? void 0 : _a.postMessage({
                    method: 'Vaadin.ServiceWorker.isConnectionLost',
                    id: 'Vaadin.ServiceWorker.isConnectionLost'
                });
            });
        }
    }
    addStateChangeListener(listener) {
        this.stateChangeListeners.add(listener);
    }
    removeStateChangeListener(listener) {
        this.stateChangeListeners.delete(listener);
    }
    loadingStarted() {
        this.state = ConnectionState.LOADING;
        this.loadingCount += 1;
    }
    loadingFinished() {
        if (this.loadingCount > 0) {
            this.loadingCount -= 1;
            if (this.loadingCount === 0) {
                this.state = ConnectionState.CONNECTED;
            }
        }
    }
    get state() {
        return this.connectionState;
    }
    set state(newState) {
        if (newState !== this.connectionState) {
            const prevState = this.connectionState;
            this.connectionState = newState;
            this.loadingCount = 0;
            for (const listener of this.stateChangeListeners) {
                listener(prevState, this.connectionState);
            }
        }
    }
    get online() {
        return this.connectionState === ConnectionState.CONNECTED || this.connectionState === ConnectionState.LOADING;
    }
    get offline() {
        return !this.online;
    }
    serviceWorkerMessageListener(event) {
        // Handle JSON-RPC response from service worker
        if (typeof event.data === 'object' && event.data.id === 'Vaadin.ServiceWorker.isConnectionLost') {
            if (event.data.result === true) {
                this.state = ConnectionState.CONNECTION_LOST;
            }
            // Cleanup: remove event listener upon receiving response
            navigator.serviceWorker.removeEventListener('message', this.serviceWorkerMessageListener);
        }
    }
}
const $wnd = window;
if (!((_a = $wnd.Vaadin) === null || _a === void 0 ? void 0 : _a.connectionState)) {
    $wnd.Vaadin = $wnd.Vaadin || {};
    $wnd.Vaadin.connectionState = new ConnectionStateStore(navigator.onLine ? ConnectionState.CONNECTED : ConnectionState.CONNECTION_LOST);
}
//# sourceMappingURL=ConnectionState.js.map