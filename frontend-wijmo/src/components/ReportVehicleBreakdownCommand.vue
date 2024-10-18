<template>

    <v-card outlined>
        <v-card-title>
            ReportVehicleBreakdown
        </v-card-title>

        <v-card-text>
            <String label="Model" v-model="value.model" :editMode="editMode"/>
            <String label="RegistrationNumber" v-model="value.registrationNumber" :editMode="editMode"/>
            <Boolean label="InUse" v-model="value.inUse" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="reportVehicleBreakdown"
            >
                ReportVehicleBreakdown
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
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
        name: 'ReportVehicleBreakdownCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.model = '';
            this.value.registrationNumber = '';
            this.value.inUse = false;
        },
        watch: {
        },
        methods: {
            reportVehicleBreakdown() {
                this.$emit('reportVehicleBreakdown', this.value);
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

