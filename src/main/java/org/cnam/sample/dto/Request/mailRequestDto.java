package org.cnam.sample.dto.Request;
import java.util.Map;

public class mailRequestDto {

        private String _serviceName;
        private Map<String,String> _values;
        private String _recipient;


        public mailRequestDto (String serviceName, Map<String,String> values,String recipient) {

            this._serviceName = serviceName;
            this._values = values;
            this._recipient = recipient;
        }

        public mailRequestDto () {

        }

        public String get_serviceName() {
            return _serviceName;
        }

        public void set_serviceName(String serviceName) {
            this._serviceName = serviceName;
        }

        public Map<String, String> get_values() {
            return _values;
        }

        public void set_values(Map<String, String> values) {
            this._values = values;
        }

        public String get_recipient() {
            return _recipient;
        }

        public void set_recipient(String recipient) {
            this._recipient = recipient;
        }
}
