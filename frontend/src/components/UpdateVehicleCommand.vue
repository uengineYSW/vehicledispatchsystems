<template>
    <v-card outlined>
        <v-card-title>
            UpdateVehicle
        </v-card-title>

        <v-card-text>
            <String label="Manufacturer" v-model="value.manufacturer" :editMode="editMode"/>
            <String label="Model" v-model="value.model" :editMode="editMode"/>
            <String label="Usage" v-model="value.usage" :editMode="editMode"/>
            <String label="Company" v-model="value.company" :editMode="editMode"/>
            <String label="RegistrationNumber" v-model="value.registrationNumber" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="updateVehicle"
            >
                UpdateVehicle
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'UpdateVehicleCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.manufacturer = '';
            this.value.model = '';
            this.value.usage = '';
            this.value.company = '';
            this.value.registrationNumber = '';
        },
        watch: {
        },
        methods: {
            updateVehicle() {
                this.$emit('updateVehicle', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

